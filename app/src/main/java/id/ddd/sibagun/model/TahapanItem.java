package id.ddd.sibagun.model;

public class TahapanItem {
	private String output;
	private String Id_Kel;
	private String Uraian;
	private String Tgl_Target;
	private String Id_Tahapan;
	private String Tahun;
	private String data_draft;
	private String data_final;

	public void setOutput(String output){
		this.output = output;
	}

	public String getOutput(){
		return output;
	}

	public void setIdKel(String Id_Kel){
		this.Id_Kel = Id_Kel;
	}

	public String getIdKel(){
		return Id_Kel;
	}

	public void setUraian(String Uraian){
		this.Uraian = Uraian;
	}

	public String getUraian(){
		return Uraian;
	}

	public void setTglTarget(String Tgl_Target){
		this.Tgl_Target = Tgl_Target;
	}

	public String getTglTarget(){
		return Tgl_Target;
	}

	public void setIdTahapan(String Id_Tahapan){
		this.Id_Tahapan = Id_Tahapan;
	}

	public String getIdTahapan(){
		return Id_Tahapan;
	}

	public void setTahun(String Tahun){
		this.Tahun = Tahun;
	}

	public String getTahun(){
		return Tahun;
	}

	public void setDataDraft(String data_draft){
		this.data_draft = data_draft;
	}

	public String getDataDraft(){
		return data_draft;
	}

	public void setDataFinal(String data_final){
		this.data_final = data_final;
	}

	public String getDataFinal(){
		return data_final;
	}

	@Override
 	public String toString(){
		return 
			"TahapanItem{" +
			"output = '" + output + '\'' + 
			",id_Kel = '" + Id_Kel + '\'' + 
			",Uraian = '" + Uraian + '\'' + 
			",tgl_Target = '" + Tgl_Target + '\'' + 
			",id_Tahapan = '" + Id_Tahapan + '\'' + 
			",Tahun = '" + Tahun + '\'' + 
			",data_draft = '" + data_draft + '\'' + 
			",data_final = '" + data_final + '\'' + 
			"}";
		}
}
