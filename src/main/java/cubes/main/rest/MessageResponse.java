package cubes.main.rest;




public class MessageResponse {
	// kad se desi greska u kontroleru cemo vratiti objekat ove klase

	
	private int code;
	private String message;
	private long time; // timestamp u ms
	
	
	public MessageResponse() {
		
	}
	public MessageResponse(int code, String message, long time) {
		super();
		this.code = code;
		this.message = message;
		this.time = time;
	}
	
	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return message+" - "+code;
	}
	
	
}
