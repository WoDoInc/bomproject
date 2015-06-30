package com.java.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.java.error.NotFoundException;
import com.java.error.RestErrorHandler;
import com.java.rest.Part;
import com.java.rest.PartsListController;
import com.java.rest.PartsListDTO;
import com.java.rest.PartsListService;

@RunWith(MockitoJUnitRunner.class)
public class PartsListControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private static final String ID = "id";
    private static final Long COUNT = 42L;
    private static final Part PART = new Part();
    static {
    	PART.setId("ZZZ");
    	PART.setType("Capacitor");
    	PART.setCapacitance("10µF");
    }

    @Mock
    private PartsListService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PartsListController(service))
                .setHandlerExceptionResolvers(withExceptionControllerAdvice())
                .build();
    }

    private ExceptionHandlerExceptionResolver withExceptionControllerAdvice() {
        final ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(final HandlerMethod handlerMethod,
                                                                              final Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(RestErrorHandler.class).resolveMethod(exception);
                if (method != null) {
                    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
                    messageSource.setBasename("messages");
                    return new ServletInvocableHandlerMethod(new RestErrorHandler(messageSource), method);
                }
                return super.getExceptionHandlerMethod(handlerMethod, exception);
            }
        };
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

    @Test
    public void delete_PartsListsEntryNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        when(service.delete(ID)).thenThrow(new NotFoundException(ID));

        mockMvc.perform(delete("/api/partslist/{id}", ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_PartsListsEntryFound_ShouldReturnResponseStatusOk() throws Exception {
        PartsListDTO deleted = new PartsListDTOBuilder()
                .id(ID)
                .build();

        when(service.delete(ID)).thenReturn(deleted);

        mockMvc.perform(delete("/api/partslist/{id}", ID))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll_ShouldReturnResponseStatusOk() throws Exception {
        mockMvc.perform(get("/api/partslist"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll_OnePartsListsEntryFound_ShouldReturnListThatContainsOnePartsListEntryAsJson() throws Exception {
        PartsListDTO found = new PartsListDTOBuilder()
                .id(ID)
                .count(COUNT)
                .build();

        when(service.findAll()).thenReturn(Arrays.asList(found));

        mockMvc.perform(get("/api/partslist"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(ID)))
                .andExpect(jsonPath("$[0].count", is(42)));
    }

    @Test
    public void findById_PartsListsEntryFound_ShouldReturnResponseStatusOk() throws Exception {
        PartsListDTO found = new PartsListDTOBuilder().build();

        when(service.findById(ID)).thenReturn(found);

        mockMvc.perform(get("/api/partslist/{id}", ID))
                .andExpect(status().isOk());
    }

    @Test
    public void findById_PartsListsEntryFound_ShouldTheInformationOfFoundPartsListsEntryAsJson() throws Exception {
        PartsListDTO found = new PartsListDTOBuilder()
                .id(ID)
                .count(COUNT)
                .build();

        when(service.findById(ID)).thenReturn(found);

        mockMvc.perform(get("/api/partslist/{id}", ID))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.count", is(42)));
    }

    @Test
    public void findById_PartsListsEntryNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        when(service.findById(ID)).thenThrow(new NotFoundException(ID));

        mockMvc.perform(get("/api/partslist/{id}", ID))
                .andExpect(status().isNotFound());
    }
}
