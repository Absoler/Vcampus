package vo;

import java.io.Serializable;

public class LastOperState implements Serializable {
	private boolean isSuccess;
	//�ӷ���˷��͸��ͻ��ˣ�������ʾ֮ǰ�Ĳ����ɹ���񣬳ɹ����������
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
