package com.example.businessModelCustomer.Service;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import com.example.businessModelCustomer.entity.Office;
import com.example.businessModelCustomer.exception.FieldNotFoundException;
import com.example.businessModelCustomer.exception.OfficeCodeNotFoundException;
import com.example.businessModelCustomer.exception.OfficeNotFoundException;
import com.example.businessModelCustomer.repository.OfficeRepsoitory;
import com.example.businessModelCustomer.service.OfficeServiceImpl;
 
 
class OfficeServiceImplTest {
 
    @Mock
    private OfficeRepsoitory officeRepository;
 
    @InjectMocks
    private OfficeServiceImpl officeService;
 
    private Office office1, office2;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
 
        // Setup sample office data
        office1 = new Office();
        office1.setOfficeCode("1");
        office1.setAddressLine1("100 Market Street");
        office1.setAddressLine2("Suite 300");
        office1.setCity("San Francisco");
        office1.setCountry("USA");
        office1.setPhone("+1 650 219 4782");
        office1.setPostalCode("94080");
        office1.setState("CA");
        office1.setTerritory("NA");
 
        office2 = new Office();
        office2.setOfficeCode("2");
        office2.setAddressLine1("1550 Court Place");
        office2.setAddressLine2("Suite 102");
        office2.setCity("Boston");
        office2.setCountry("USA");
        office2.setPhone("+1 215 837 0825");
        office2.setPostalCode("02107");
        office2.setState("MA");
        office2.setTerritory("NA");
    }
 
    @Test
    void testGetAllOffices() {
        when(officeRepository.findAll()).thenReturn(Arrays.asList(office1, office2));
 
        List<Office> offices = officeService.getAllOffices();
 
        assertNotNull(offices);
        assertEquals(2, offices.size());
        assertTrue(offices.contains(office1));
        assertTrue(offices.contains(office2));
        verify(officeRepository, times(1)).findAll();
    }
 
    @Test
    void testFindByOfficeCode_Success() {
        when(officeRepository.findByOfficeCode("1")).thenReturn(office1);
 
        Office office = officeService.findByOfficeCode("1");
 
        assertNotNull(office);
        assertEquals("San Francisco", office.getCity());
        verify(officeRepository, times(1)).findByOfficeCode("1");
    }
 
    @Test
    void testFindByOfficeCode_OfficeNotFound() {
        when(officeRepository.findByOfficeCode("999")).thenReturn(null);
 
        assertThrows(OfficeNotFoundException.class, () -> officeService.findByOfficeCode("999"));
        verify(officeRepository, times(1)).findByOfficeCode("999");
    }
 
    @Test
    void testAddNewOffice_Success() {
        Office newOffice = new Office();
        newOffice.setOfficeCode("3");
        newOffice.setAddressLine1("523 East 53rd Street");
        newOffice.setAddressLine2("apt. 5A");
        newOffice.setCity("NYC");
        newOffice.setCountry("USA");
        newOffice.setPhone("+1 212 555 3000");
        newOffice.setPostalCode("10022");
        newOffice.setState("NY");
        newOffice.setTerritory("NA");
 
        when(officeRepository.save(any(Office.class))).thenReturn(newOffice);
 
        Office savedOffice = officeService.addNewOffice(newOffice);
 
        assertNotNull(savedOffice);
        assertEquals("NYC", savedOffice.getCity());
        verify(officeRepository, times(1)).save(newOffice);
    }
 
    @Test
    void testAddNewOffice_FieldMissing() {
        Office newOffice = new Office();
        newOffice.setAddressLine1("523 East 53rd Street"); // Missing required fields
 
        assertThrows(FieldNotFoundException.class, () -> officeService.addNewOffice(newOffice));
        verifyNoInteractions(officeRepository);
    }
 
    @Test
    void testUpdatePhoneNumber_OfficeNotFound() {
        when(officeRepository.findByOfficeCode("999")).thenReturn(null);
 
        assertThrows(OfficeNotFoundException.class, () -> officeService.updatePhoneNumber("999", "+1 650 219 9999"));
        verify(officeRepository, times(1)).findByOfficeCode("999");
        verifyNoMoreInteractions(officeRepository);
    }
 
    @Test
    void testDeleteOffice_Success() {
        when(officeRepository.findByOfficeCode("1")).thenReturn(office1);
        doNothing().when(officeRepository).deleteById("1");
 
        officeService.deleteOffice("1");
 
        verify(officeRepository, times(1)).findByOfficeCode("1");
        verify(officeRepository, times(1)).deleteById("1");
    }
 
    @Test
    void testDeleteOffice_OfficeNotFound() {
        when(officeRepository.findByOfficeCode("999")).thenReturn(null);
 
        assertThrows(OfficeCodeNotFoundException.class, () -> officeService.deleteOffice("999"));
        verify(officeRepository, times(1)).findByOfficeCode("999");
        verifyNoMoreInteractions(officeRepository);
    }
}
