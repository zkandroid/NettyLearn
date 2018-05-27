package message.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class MessageBody extends IMessageBody {
	private String jsonString = "";
	public void setJsonStringByObject(Object o1) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		setJsonString(mapper.writeValueAsString(o1));
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}
