package vo;

import java.io.Serializable;

public class LastOperState implements Serializable {
	private static final long serialVersionUID = 50000;
	private boolean isSuccess;
	//�ӷ���˷��͸��ͻ��ˣ�������ʾ֮ǰ�Ĳ����ɹ���񣬳ɹ����������
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
}
