package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    private Manufacturer m1;
    private Balloon b1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    public void initData(){
        if(!dataInitialized){
            m1 = manufacturerService.save("m1","c1","a1");
            b1 = balloonService.save("b1","d1",m1.getId(),null);
            dataInitialized = true;
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetBalloons() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/balloons");
        this.mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("balloons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("manufacturers"))
                .andExpect(MockMvcResultMatchers.view().name("listBalloons"));
    }

}
