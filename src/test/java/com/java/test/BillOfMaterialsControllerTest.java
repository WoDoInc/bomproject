package com.java.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
import com.java.rest.BillOfMaterialsController;
import com.java.rest.BillOfMaterialsDTO;
import com.java.rest.BillOfMaterialsService;
import com.java.rest.Part;
import com.java.rest.PartsList;

@RunWith(MockitoJUnitRunner.class)
public class BillOfMaterialsControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private static final List<PartsList> PARTS = new ArrayList<>();
    private static final PartsList LIST = new PartsList();
    private static final Part PART = new Part();
    static {
    	LIST.setCount(1L);
    	LIST.setPart(PART);
    	PARTS.add(LIST);
    }

    @Mock
    private BillOfMaterialsService service;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new BillOfMaterialsController(service))
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
    public void create_BillOfMaterialsEntryWithDescription_ShouldReturnTheInformationOfCreatedBillOfMaterialsEntryAsJSon() throws Exception {
        BillOfMaterialsDTO newBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(service.create(isA(BillOfMaterialsDTO.class))).then(invocationOnMock -> {
            BillOfMaterialsDTO saved = (BillOfMaterialsDTO) invocationOnMock.getArguments()[0];
            saved.setId(ID);
            return saved;
        });

        mockMvc.perform(post("/api/billofmaterials")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(newBillOfMaterialsEntry))
        )
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }

    @Test
    public void create_BillOfMaterialsEntryWithMaxLengthDescription_ShouldCreateNewBillOfMaterialsEntryWithCorrectInformation() throws Exception {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterialsDTO newBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        mockMvc.perform(post("/api/billofmaterials")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(newBillOfMaterialsEntry))
        );

        ArgumentCaptor<BillOfMaterialsDTO> createdArgument = ArgumentCaptor.forClass(BillOfMaterialsDTO.class);
        verify(service, times(1)).create(createdArgument.capture());
        verifyNoMoreInteractions(service);

        BillOfMaterialsDTO created = createdArgument.getValue();
        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(created)
                .hasNoId()
                .hasDescription(maxLengthDescription);
    }

    @Test
    public void create_BillOfMaterialsEntryWithMaxLengthDescription_ShouldReturnResponseStatusCreated() throws Exception {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterialsDTO newBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        when(service.create(isA(BillOfMaterialsDTO.class))).then(invocationOnMock -> {
            BillOfMaterialsDTO saved = (BillOfMaterialsDTO) invocationOnMock.getArguments()[0];
            saved.setId(ID);
            return saved;
        });

        mockMvc.perform(post("/api/billofmaterials")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(newBillOfMaterialsEntry))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void delete_BillOfMaterialsEntryNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        when(service.delete(ID)).thenThrow(new NotFoundException(ID));

        mockMvc.perform(delete("/api/billofmaterials/{id}", ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_BillOfMaterialsEntryFound_ShouldReturnResponseStatusOk() throws Exception {
        BillOfMaterialsDTO deleted = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .build();

        when(service.delete(ID)).thenReturn(deleted);

        mockMvc.perform(delete("/api/billofmaterials/{id}", ID))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_BillOfMaterialsEntryFound_ShouldTheInformationOfDeletedBillOfMaterialsEntryAsJson() throws Exception {
        BillOfMaterialsDTO deleted = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(service.delete(ID)).thenReturn(deleted);

        mockMvc.perform(delete("/api/billofmaterials/{id}", ID))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }

    @Test
    public void findAll_ShouldReturnResponseStatusOk() throws Exception {
        mockMvc.perform(get("/api/billofmaterials"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll_OneBillOfMaterialsEntryFound_ShouldReturnListThatContainsOneBillOfMaterialsEntryAsJson() throws Exception {
        BillOfMaterialsDTO found = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(service.findAll()).thenReturn(Arrays.asList(found));

        mockMvc.perform(get("/api/billofmaterials"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(ID)))
                .andExpect(jsonPath("$[0].description", is(DESCRIPTION)));
    }

    @Test
    public void findById_BillOfMaterialsEntryFound_ShouldReturnResponseStatusOk() throws Exception {
        BillOfMaterialsDTO found = new BillOfMaterialsDTOBuilder().build();

        when(service.findById(ID)).thenReturn(found);

        mockMvc.perform(get("/api/billofmaterials/{id}", ID))
                .andExpect(status().isOk());
    }

    @Test
    public void findById_BillOfMaterialsEntryFound_ShouldTheInformationOfFoundBillOfMaterialsEntryAsJson() throws Exception {
        BillOfMaterialsDTO found = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(service.findById(ID)).thenReturn(found);

        mockMvc.perform(get("/api/billofmaterials/{id}", ID))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)));
    }

    @Test
    public void findById_BillOfMaterialsEntryNotFound_ShouldReturnResponseStatusNotFound() throws Exception {
        when(service.findById(ID)).thenThrow(new NotFoundException(ID));

        mockMvc.perform(get("/api/billofmaterials/{id}", ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_BillOfMaterialsEntryWithMaxLengthDescription_ShouldUpdateTheInformationOfBillOfMaterialsEntry() throws Exception {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterialsDTO updatedBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        mockMvc.perform(put("/api/billofmaterials/{id}", ID)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(updatedBillOfMaterialsEntry))
        );

        ArgumentCaptor<BillOfMaterialsDTO> updatedArgument = ArgumentCaptor.forClass(BillOfMaterialsDTO.class);
        verify(service, times(1)).update(updatedArgument.capture());
        verifyNoMoreInteractions(service);

        BillOfMaterialsDTO updated = updatedArgument.getValue();
        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(updated)
                .hasId(ID)
                .hasDescription(maxLengthDescription);
    }

    @Test
    public void update_BillOfMaterialsEntryWithMaxLengthDescription_ShouldReturnResponseStatusOk() throws Exception {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterialsDTO updatedBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        when(service.create(isA(BillOfMaterialsDTO.class))).then(invocationOnMock -> (BillOfMaterialsDTO) invocationOnMock.getArguments()[0]);

        mockMvc.perform(put("/api/billofmaterials/{id}", ID)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(updatedBillOfMaterialsEntry))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void update_BillOfMaterialsEntryWithMaxLengthDescription_ShouldReturnTheInformationOfCreatedUpdatedBillOfMaterialsEntryAsJson() throws Exception {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterialsDTO updatedBillOfMaterialsEntry = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        when(service.update(isA(BillOfMaterialsDTO.class))).then(invocationOnMock -> (BillOfMaterialsDTO) invocationOnMock.getArguments()[0]);

        mockMvc.perform(put("/api/billofmaterials/{id}", ID)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(WebTestUtil.convertObjectToJsonBytes(updatedBillOfMaterialsEntry))
        )
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is(maxLengthDescription)));
    }
}
