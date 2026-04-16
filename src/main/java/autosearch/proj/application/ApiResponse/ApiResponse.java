package autosearch.proj.application.ApiResponse;

//api repsonse with generic field, always return a boolean success, and then a string message
//sometimes the generic 'data' might be List of cars, a car object, etc. 
public class ApiResponse<T> {

	private boolean success;
	private String message;
	private T data;
	
	public ApiResponse(boolean success, String message, T data){
		this.success = success;
		this.message = message;
		this.setData(data);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
