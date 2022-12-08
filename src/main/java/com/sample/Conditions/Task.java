package com.sample.Conditions;

public class Task {
	public String groupId;
	public long duration;
	
	public Task (String groupId, long duration) {
		this.groupId = groupId;
		this.duration = duration;
	}
	
    public String getGroupId() { return this.groupId; }
    
    public long getDuration() { return this.duration; }

}
