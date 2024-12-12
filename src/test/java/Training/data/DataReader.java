package Training.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
public List<HashMap<String, String>> getJsonDataToString() throws IOException
{
	//Read JSON to String
	String jsonContent = FileUtils.readFileToString(new File
			(System.getProperty("user.dir")+ "//src//test//java//Training//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
	//Convert String to HashMap
	//Add JACKSON DATABIND Dependerncy to pom.xml
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	return data;
}
}
