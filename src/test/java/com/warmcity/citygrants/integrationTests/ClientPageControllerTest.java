package com.warmcity.citygrants.integrationTests;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warmcity.citygrants.dto.BudgetDTO;
import com.warmcity.citygrants.dto.DescriptionDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.main.CitygrantsApplication;
import com.warmcity.citygrants.services.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitygrantsApplication.class)
@AutoConfigureMockMvc
public class ClientPageControllerTest {

  private static final int SOMEVALIDNUMBER = 5;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ProjectService projectService;

  @Test
  public void saveProjectTest() throws Exception {

    mvc.perform(
        post("/client/project").contentType(MediaType.APPLICATION_JSON).content(asJsonString(prepareDummyProjectDTO())))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id", notNullValue()));
  }

  @Test
  public void saveProjectWithExistingNameTest() throws Exception {

    ProjectApplicationDTO projectDTO = prepareDummyProjectDTO();
    String id = projectService.saveProject(projectDTO);
    mvc.perform(post("/client/project").contentType(MediaType.APPLICATION_JSON).content(asJsonString(projectDTO)))
        .andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", notNullValue()));
    projectService.deleteProject(id);
  }

  @Test
  public void isUniqueProjectNameTest() throws Exception {

    String name = randomAlphabetic(SOMEVALIDNUMBER);
    ProjectApplicationDTO projectDTO = prepareDummyProjectDTO();
    projectDTO.getDescription().setName(name);
    String id = projectService.saveProject(projectDTO);
    mvc.perform(get("/client/project/isUniqName/{projectName}", name)).andExpect(status().is2xxSuccessful())
        .andExpect(content().string(containsString("false")));
    projectService.deleteProject(id);
  }

  @Test
  public void uploadFileTest() throws Exception {

    String id = projectService.saveProject(prepareDummyProjectDTO());

    MockMultipartFile images = new MockMultipartFile("images", "image.jpg", MediaType.IMAGE_JPEG_VALUE,
        randomAlphabetic(SOMEVALIDNUMBER).getBytes());
    MockMultipartFile pdfDocs = new MockMultipartFile("pdfDocs", "pdf.pdf", MediaType.APPLICATION_PDF_VALUE,
        randomAlphabetic(SOMEVALIDNUMBER).getBytes());
    mvc.perform(fileUpload("/client/project/file").file(images).file(pdfDocs).param("id", id))
        .andExpect(status().is2xxSuccessful());

    projectService.deleteProject(id);

  }

  @Test
  public void uploadNotValidTypeFileTest() throws Exception {

    String id = projectService.saveProject(prepareDummyProjectDTO());

    MockMultipartFile images = new MockMultipartFile("images", "image.jpg", MediaType.IMAGE_JPEG_VALUE,
        randomAlphabetic(SOMEVALIDNUMBER).getBytes());
    MockMultipartFile pdfDocs = new MockMultipartFile("pdfDocs", "pdf.xml", MediaType.TEXT_XML_VALUE,
        randomAlphabetic(SOMEVALIDNUMBER).getBytes());
    mvc.perform(fileUpload("/client/project/file").file(images).file(pdfDocs).param("id", id))
        .andExpect(status().is4xxClientError());

    projectService.deleteProject(id);
  }

  private static String asJsonString(final Object obj) {

    try {
      return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private ProjectApplicationDTO prepareDummyProjectDTO() {

    ProjectApplicationDTO projectDTO = new ProjectApplicationDTO();
    DescriptionDTO descriptionDTO = new DescriptionDTO();
    descriptionDTO.setName(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setRequestedBudget(randomNumeric(SOMEVALIDNUMBER));
    descriptionDTO.setOrganizationName(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setTheme(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setRequiredTime(randomNumeric(SOMEVALIDNUMBER));
    descriptionDTO.setCoordinatorName(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setCoordinatorPhone("+380962609117");
    descriptionDTO.setCoordinatorEmail("test@mail.com");
    descriptionDTO.setProjectMembers(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setExpirienceDescription(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setActuality(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setAddress(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setWebaddress(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setGoal(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setActuality(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setFullDescription(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setTargetGroup(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setExpectedResults(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setRequiredPermissions(randomAlphabetic(SOMEVALIDNUMBER));
    descriptionDTO.setPartners(randomAlphabetic(SOMEVALIDNUMBER));
    BudgetDTO budgetDTO = new BudgetDTO();
    budgetDTO.setCostItemsAdministrative(new ArrayList<>());
    budgetDTO.setCostItemsAdvertising(new ArrayList<>());
    budgetDTO.setCostItemsFee(new ArrayList<>());
    budgetDTO.setCostItemsMaterial(new ArrayList<>());
    budgetDTO.setCostItemsNutrition(new ArrayList<>());
    budgetDTO.setCostItemsOthers(new ArrayList<>());
    budgetDTO.setCostItemsRent(new ArrayList<>());
    budgetDTO.setCostItemsTransport(new ArrayList<>());
    projectDTO.setDescription(descriptionDTO);
    projectDTO.setBudget(budgetDTO);
    return projectDTO;

  }

}
