package life.genny.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.javamoney.moneta.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import life.genny.qwanda.DateTimeDeserializer;
import life.genny.qwanda.MoneyDeserializer;
import life.genny.qwanda.attribute.Attribute;
import life.genny.qwanda.attribute.AttributeText;
import life.genny.qwanda.datatype.LocalDateAdapter;
import life.genny.qwanda.attribute.AttributeDouble;
import life.genny.qwanda.attribute.AttributeLong;
import life.genny.qwanda.attribute.AttributeDateTime;
import life.genny.qwanda.attribute.AttributeDate;
import life.genny.qwanda.attribute.AttributeBoolean;
import life.genny.qwanda.entity.Person;
import life.genny.qwanda.entity.Product;
import life.genny.qwanda.exception.BadDataException;
import life.genny.qwanda.message.QDataBaseEntityMessage;
import life.genny.qwanda.validation.Validation;

import life.genny.test.qwanda.util.JsonUtils;


public class baseEntityTest {

	Person person;
	Product product;
	
	@Before
	public void setup() throws BadDataException {
		
		AttributeText attributeText1 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST1","Test 1");
		AttributeText attributeText2 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST2","Test 1");
		AttributeText attributeText3 = new AttributeText(AttributeText.getDefaultCodePrefix()+"TEST3","Test 1");
		
		Attribute attributeDouble = new AttributeDouble(AttributeDouble.getDefaultCodePrefix()+"TEST4","Test Double 4");
		Attribute attributeLong = new AttributeLong(AttributeLong.getDefaultCodePrefix()+"TEST5","Test Long 5");	
		Attribute attributeBoolean = new AttributeBoolean(AttributeBoolean.getDefaultCodePrefix()+"TEST6","Test Boolean 6");	
		Attribute attributeDateTime = new AttributeDateTime(AttributeDateTime.getDefaultCodePrefix()+"TEST7","Test DateTiume 7");	
		Attribute attributeDate = new AttributeDate(AttributeDate.getDefaultCodePrefix()+"TEST8","Test Date 8");	
		
		
		person = new Person("Barry Allen");
		
		person.addAttribute(attributeText1, 1.0);
		person.addAttribute(attributeText2, 0.8);
		person.addAttribute(attributeText3, 0.6, "3147");
		person.addAttribute(attributeDouble, 0.6, 3.141);
		person.addAttribute(attributeLong, 0.6, 3147L);
		person.addAttribute(attributeBoolean, 0.6, true);
		person.addAttribute(attributeDateTime, 0.6, LocalDateTime.of(2017, Month.JUNE, 20, 10, 13));
		person.addAttribute(attributeDate, 0.6, LocalDate.of(2017, Month.JUNE, 20));

		
		product = new Product(Product.getDefaultCodePrefix()+"TEST_PRODUCT","Test Product");
		
		product.addAttribute(attributeText1, 1.0);
		product.addAttribute(attributeText2, 0.8);
		product.addAttribute(attributeText3, 0.6, "3147");
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
 
 @Test
 public void validationJsonTest()
 {
	 Validation validation = new Validation("VLD_TEST","Test Validatoin","GRP_SELECTION_TEST",false,true);
	 String jsonString = JsonUtils.set(validation);
	 System.out.println("Json Validation  = "+jsonString);
 }
 
 
 @Test
 public void beTest()
 {
	 String incoming = "{\"items\":[{\"baseEntityAttributes\":[{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_EMAIL\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"gayatri.raghavan@outcome.life\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_POSTCODE\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"3053\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ASSEMBLY_USER_ID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"eyJ1c2VyQ29kZSI6IlBFUl9VU0VSMSJ9\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_DOB\",\"created\":\"2018-01-31T08:31:59\",\"valueDate\":{},\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_OWNER\",\"created\":\"2018-01-31T08:31:58\",\"valueBoolean\":true,\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_UUID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"9e4dd67d-c75c-4ab9-b26b-40ce0ba0e034\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":true},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_STATE\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"VIC\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_CITY\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"Melbourne\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_LASTNAME\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"Cavanagh\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ASSEMBLY_COMPANY_ID\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"71f464cf-d9b1-4547-91a5-9ee80a7adb39\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_FULL\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"121 Cardigan Street, Carlton, VIC\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_FIRSTNAME\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"Tom\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_COUNTRY\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"AU\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_FB_BASIC\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":0.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_KEYCLOAK_UUID\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"029b2ab9-e6ee-47b9-aeff-33fd537a1a07\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_ADDRESS_ADDRESS1\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"121 Cardigan Street\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_MOBILE\",\"created\":\"2018-01-31T08:31:58\",\"valueString\":\"61424664610\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_TEST\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"I am a test\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_DRIVING_LICENSE\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_USERNAME\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"user1\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_GENDER\",\"created\":\"2018-01-31T08:31:57\",\"valueString\":\"null\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false},{\"baseEntityCode\":\"PER_USER1\",\"attributeCode\":\"PRI_IMAGE_URL\",\"created\":\"2018-01-31T08:31:59\",\"valueString\":\"https://i.imgur.com/nRIZ93K.png\",\"weight\":1.0,\"inferred\":false,\"privacyFlag\":false}],\"code\":\"PER_USER1\",\"created\":\"2018-01-31T08:31:57\",\"id\":90,\"name\":\"Josh Mullens\",\"realm\":\"genny\"}],\"parentCode\":\"\",\"linkCode\":\"\",\"total\":1,\"returnCount\":1,\"data_type\":\"BaseEntity\",\"delete\":false,\"msg_type\":\"DATA_MSG\"}";
 
	   GsonBuilder gsonBuilder = new GsonBuilder();
	    Gson gson = gsonBuilder
	    		.registerTypeAdapter(Money.class, new MoneyDeserializer())
	    		.registerTypeAdapter(LocalDateTime.class, new DateTimeDeserializer()).setPrettyPrinting()
	    	    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	    
	    QDataBaseEntityMessage item = (QDataBaseEntityMessage)gson.fromJson(incoming, QDataBaseEntityMessage.class);
	    System.out.println("Item ="+item);
 
 }
 
 
}
