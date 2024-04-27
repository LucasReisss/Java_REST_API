package com.example.demo.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.data.vo.v1.PersonVO;
import com.example.demo.exceptions.RequiredObjectlsNullException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.PersonServices;
import com.example.demo.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonServices services;
	
	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void testFindAll() {
//		List<Person> list = input.mockEntityList();
//
//		when(repository.findAll()).thenReturn(list);
//
//		var people = services.findAll(pageable);
//
//		assertNotNull(people);
//
//		assertEquals(14, people.size());
//
//		var personOne = people.get(1);
//		assertNotNull(personOne);
//		assertNotNull(personOne.getKey());
//		assertNotNull(personOne.getLinks());
//		assertTrue(personOne.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", personOne.getAddress());
//		assertEquals("First Name Test1", personOne.getFirstName());
//		assertEquals("Last Name Test1", personOne.getLastName());
//		assertEquals("Female", personOne.getGender());
//
//		var personFour = people.get(4);
//		assertNotNull(personFour);
//		assertNotNull(personFour.getKey());
//		assertNotNull(personFour.getLinks());
//		assertTrue(personFour.toString().contains("[</api/person/v1/4>;rel=\"self\"]"));
//		assertEquals("Addres Test4", personFour.getAddress());
//		assertEquals("First Name Test4", personFour.getFirstName());
//		assertEquals("Last Name Test4", personFour.getLastName());
//		assertEquals("Male", personFour.getGender());
//
//		var personSeven = people.get(7);
//		assertNotNull(personSeven);
//		assertNotNull(personSeven.getKey());
//		assertNotNull(personSeven.getLinks());
//		assertTrue(personSeven.toString().contains("[</api/person/v1/7>;rel=\"self\"]"));
//		assertEquals("Addres Test7", personSeven.getAddress());
//		assertEquals("First Name Test7", personSeven.getFirstName());
//		assertEquals("Last Name Test7", personSeven.getLastName());
//		assertEquals("Female", personSeven.getGender());
//
//	}
	
	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = services.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}


	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		
		Person persisted = entity;
		entity.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = services.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullperson() {
		
		Exception exception = assertThrows(RequiredObjectlsNullException.class, () -> {
			services.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person persisted = entity;
		entity.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = services.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullperson() {
		
		Exception exception = assertThrows(RequiredObjectlsNullException.class, () -> {
			services.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		services.delete(1L);
	}

}
