package edu.nju.healthClub.model.user;

public enum VIPState {
	UNACTIVE("未激活"),ACTIVE("激活状态"),PAUSE("暂停服务"),STOP("停止服务"),FREEZE("冻结状态");
	private String string;
	private VIPState(String string){
		this.string = string;
	}
	public String toString(){
		return this.string;
	}
}
