package com.rixon.lms.domain;

public class Role {
	
	private int id;
	private String role;
	private String description;
	
	private Role(int id,String role,String description) {
		this.id = id;
		this.role = role;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	@Override
	public String toString() {
		return "Role [description=" + description + ", id=" + id + ", role="
				+ role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}



	public static class RoleBuilder {
		private int id;
		private String role;
		private String description;
		
		public RoleBuilder setId(int id){
			this.id = id;
			return this;
		}
		
		public RoleBuilder setRole(String role){
			this.role = role;
			return this;
		}
	
		public RoleBuilder setDescription(String description){
			this.description = description;
			return this;
		}

		public Role createRole(){
		   return new Role(id,role,description);	
		}
	}
}
