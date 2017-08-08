package life.genny.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.entity.Product;
import life.genny.qwanda.exception.BadDataException;
import life.genny.test.qwanda.util.JsonUtils;


public class baseEntityTest {

	Person person;
	Product product;
	
	@Before
	public void setup() throws BadDataException {
		
		AttributeText attributeText1 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST1","Test 1");
		AttributeText attributeText2 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST2","Test 1");
		AttributeText attributeText3 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST3","Test 1");
		
		person = new Person("Barry Allen");
		
		person.addAttribute(attributeText1, 1.0);
		person.addAttribute(attributeText2, 0.8);
		person.addAttribute(attributeText3, 0.6, 3147);
		
		product = new Product(Product.getDefaultCodePrefix()+"TEST_PRODUCT","Test Product");
		
		product.addAttribute(attributeText1, 1.0);
		product.addAttribute(attributeText2, 0.8);
		product.addAttribute(attributeText3, 0.6, 3147);
	}

	@After
	public void tearDown() {
		person = null;
		product = null;
	}
	
	@Test
	public void entityAttributeTest()
	{	
		// SHow the person details
		System.out.println(person);
		
		// CHeck some attributes exist
		assertFalse(person.containsEntityAttribute(AttributeText.getDefaultCodePrefix()+"TEST0"));
		assertFalse(product.containsEntityAttribute(AttributeText.getDefaultCodePrefix()+"TEST0"));

	}
	


	@Test(expected = BadDataException.class)
	public void shouldThrowBadDataExceptionOnAttribute() throws BadDataException {
		person.addAttribute(null, 1.0); // weight of zero, attribute is null
		product.addAttribute(null, 1.0); // weight of zero, attribute is null
		
	}

	@Test(expected = BadDataException.class)
	public void shouldThrowBadDataExceptionOnWeight() throws BadDataException {
		AttributeText attributeText = new AttributeText(AttributeText.getDefaultCodePrefix()+"WEIGHT_TEST","WeightText");

		person.addAttribute(attributeText, null);
	}

	@Test
	public void linkBaseEntity()
	{
		String json = JsonUtils.set(person);
		System.out.println(json);
	}
	

	@Test
	public void testFindAttribute() {
		assertThat(person.findEntityAttribute(Attribute.getDefaultCodePrefix()+"TEST1").isPresent(), equalTo(true));
	}

	@Test
	public void testRemoveAttribute() throws BadDataException {
		Attribute attributeText = new AttributeText(AttributeText.getDefaultCodePrefix()+"REMOVE_TEST","RemoveText");

		person.addAttribute(attributeText);

		assumeThat(person.findEntityAttribute(Attribute.getDefaultCodePrefix()+"REMOVE_TEST").isPresent(), is(true));
		person.removeAttribute(Attribute.getDefaultCodePrefix()+"REMOVE_TEST");
		assumeThat(person.findEntityAttribute(Attribute.getDefaultCodePrefix()+"REMOVE_TEST").isPresent(), is(false));
	}

	@Test
	public void jsonTest()
	{
		
	}
	
//	@Test
//	public void testUpdateStudentGpa() throws BadDataException {
//		person.updateStudentGpa("Jane", "Doe", 3.8);
//		assertThat(
//				person.findStudent("Jane", "Doe").otherwiseThrow(
//						NullPointerException.class).gpa.otherwise(0d),
//				equalTo(3.8));
//	}
//
//	@Test
//	public void testGetHighestGPA() {
//		assertThat(person.getHighestGPA(), equalTo(maybe(4.0)));
//	}
//
//	@Test
//	public void testGetLowestGPA() {
//		assertThat(person.getLowestGPA(), equalTo(maybe(2.2)));
//	}
//
//	@Test
//	public void testGetAverageGPA() {
//		assertThat(person.getAverageGPA(), equalTo(maybe(3.31667)));
//	}
//
//	@Test
//	public void testGetStudentsWithNoGPA() {
//		Iterable<Student> students = person.getStudentsWithNoGPA();
//		Iterator<Student> iterator = students.iterator();
//		assumeThat(iterator, notNullValue());
//		assertThat(iterator.next(), equalTo(new Student("John", "Doe",
//				StudentType.PREMED, Maybe.theAbsenceOfA(Double.class))));
//	}
//	
//	@Test
//	public void shouldGet2Students() {
//		int count = 0;
//		for (Student s :person.getStudentsWithHighestGPA()) {
//			count++;
//			System.out.println(s.firstName);
//		}
//		assertThat(count, is(2));
//	}
	
 @Test
 public void jsonLocalDateTimeTest()
 {
	 LocalDateTime now = LocalDateTime.of(2017, Month.JUNE, 20, 10, 13);
	 
	 String jsonString = JsonUtils.set(now);
	 System.out.println("Json LocalDateTime = "+jsonString);
 }
}
