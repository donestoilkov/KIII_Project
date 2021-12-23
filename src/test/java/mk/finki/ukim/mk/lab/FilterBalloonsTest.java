package mk.finki.ukim.mk.lab;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.impl.BalloonServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class FilterBalloonsTest {

    @Mock
    private BalloonRepository balloonRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    private BalloonService balloonService;

    private Manufacturer manufacturer1;
    private Manufacturer manufacturer2;

    private Balloon balloon1 = new Balloon("b1", "d1", manufacturer1);
    private Balloon balloon2 = new Balloon("b2", "d2", manufacturer2);


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        manufacturer1 = new Manufacturer("m1", "USA", "a1");
        manufacturer2 = new Manufacturer("m2", "Macedonia", "a2");

        balloon1 = new Balloon("b1", "d1", manufacturer1);
        balloon2 = new Balloon("b2", "d2", manufacturer2);


        Mockito.when(this.balloonRepository.findAllByManufacturer_Name(Mockito.anyString())).thenReturn(List.of(balloon1));

        Mockito.when(this.balloonRepository.findAllByManufacturer_Country(Mockito.anyString())).thenReturn(List.of(balloon2));
        balloonService = Mockito.spy(new BalloonServiceImpl(this.balloonRepository,this.manufacturerRepository));
    }

    @Test
    public void testFilterByCountry(){
        Assert.assertEquals("Arrays not equal", List.of(balloon2),this.balloonService.filteredByManufacturerNameOrCountry(null,"Macedonia"));
        
        Mockito.verify(this.balloonService).filteredByManufacturerNameOrCountry(null,"Macedonia");
    }

    @Test
    public void testFilterByName(){
        Assert.assertEquals("Arrays are not equal", List.of(balloon1), this.balloonService.filteredByManufacturerNameOrCountry("m1",null));

        Mockito.verify(this.balloonService).filteredByManufacturerNameOrCountry("m1",null);
    }

    @Test
    public void testFilterForNull(){
        Assert.assertNull("Arrays not equal", this.balloonService.filteredByManufacturerNameOrCountry(null, null));
        Mockito.verify(this.balloonService).filteredByManufacturerNameOrCountry(null,null);
    }

}
