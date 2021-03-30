package vo;

import java.io.Serializable;

public class LastOperState implements Serializable {
	private static final long serialVersionUID = 50000;
	private boolean isSuccess;
	//从服务端发送给客户端，用来表示之前的操作成功与否，成功则更新数据
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
