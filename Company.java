package com.orderprocessing.beans;

public class Company  {
	private String companyId;
        private String companyName;
        private String companyAddress;
        private String companyCity;
        private String companyGST_Number;
        
        public Company() {
                  super();
        }
	
        public Company(String companyId, String companyName, String companyAddress, String companyCity, String companyGST_Number) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyCity = companyCity;
		this.companyGST_Number = companyGST_Number;
        }

         
        public String getcompanyId() {
		return companyId;
	}
	public void setcompanyId(String companyId) {
		this.companyId = companyId;
	}
        public String getcompanyName() {
		return companyName;
	}
	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}
        public String getcompanyAddress() {
		return companyAddress;
	}
	public void setcompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getcompanyCity() {
		return companyCity;
	}
	public void setcompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
        public String getcompanyGST_Number() {
		return companyGST_Number;
	}
	public void setcompanyGST_Number(String companyGST_Number) {
		this.companyGST_Number = companyGST_Number;
	}

        
        @Override
	public String toString() {
		return "CompanyImpl [companyId=" + companyId + ", companyName=" + companyName + ", companyAddress="
				+ companyAddress + ", companyCity=" + companyCity + ", companyGST_Number=" + companyGST_Number + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((companyCity == null) ? 0 : companyCity.hashCode());
		result = prime * result + ((companyGST_Number == null) ? 0 : companyGST_Number.hashCode());
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
		Company other = (Company) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (companyCity == null) {
			if (other.companyCity != null)
				return false;
		} else if (!companyCity.equals(other.companyCity))
			return false;
		if (companyGST_Number == null) {
			if (other.companyGST_Number != null)
				return false;
		} else if (!companyGST_Number.equals(other.companyGST_Number))
			return false;
		return true;
	}

}
	 