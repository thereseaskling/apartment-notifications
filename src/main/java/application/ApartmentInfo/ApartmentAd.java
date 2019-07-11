package application.ApartmentInfo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApartmentAd implements Serializable {

  @JsonProperty("AnnonsId")
  private Integer annonsId;
  @JsonProperty("Stadsdel")
  private String stadsdel;
  @JsonProperty("Gatuadress")
  private String gatuadress;
  @JsonProperty("Kommun")
  private String kommun;
  @JsonProperty("Vaning")
  private Integer vaning;
  @JsonProperty("AntalRum")
  private Integer antalRum;
  @JsonProperty("Yta")
  private Integer yta;
  @JsonProperty("Hyra")
  private Integer hyra;
  @JsonProperty("AnnonseradTill")
  private String annonseradTill;
  @JsonProperty("AnnonseradFran")
  private String annonseradFran;
  @JsonProperty("Url")
  private String url;
  @JsonProperty("Antal")
  private Integer antal;
  @JsonProperty("Balkong")
  private Boolean balkong;
  @JsonProperty("Hiss")
  private Boolean hiss;
  @JsonProperty("Nyproduktion")
  private Boolean nyproduktion;
  @JsonProperty("Ungdom")
  private Boolean ungdom;
  @JsonProperty("Student")
  private Boolean student;
  @JsonProperty("Senior")
  private Boolean senior;
  @JsonProperty("Korttid")
  private Boolean korttid;
  @JsonProperty("Vanlig")
  private Boolean vanlig;
  @JsonProperty("Bostadssnabben")
  private Boolean bostadssnabben;
  @JsonProperty("Ko")
  private String ko;
  @JsonProperty("KoNamn")
  private String koNamn;
  @JsonProperty("Lagenhetstyp")
  private String lagenhetstyp;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("AnnonsId")
  public Integer getAnnonsId() {
    return annonsId;
  }

  @JsonProperty("AnnonsId")
  public void setAnnonsId(Integer annonsId) {
    this.annonsId = annonsId;
  }

  @JsonProperty("Stadsdel")
  public String getStadsdel() {
    return stadsdel;
  }

  @JsonProperty("Stadsdel")
  public void setStadsdel(String stadsdel) {
    this.stadsdel = stadsdel;
  }

  @JsonProperty("Gatuadress")
  public String getGatuadress() {
    return gatuadress;
  }

  @JsonProperty("Gatuadress")
  public void setGatuadress(String gatuadress) {
    this.gatuadress = gatuadress;
  }

  @JsonProperty("Kommun")
  public String getKommun() {
    return kommun;
  }

  @JsonProperty("Kommun")
  public void setKommun(String kommun) {
    this.kommun = kommun;
  }

  @JsonProperty("Vaning")
  public Integer getVaning() {
    return vaning;
  }

  @JsonProperty("Vaning")
  public void setVaning(Integer vaning) {
    this.vaning = vaning;
  }

  @JsonProperty("AntalRum")
  public Integer getAntalRum() {
    return antalRum;
  }

  @JsonProperty("AntalRum")
  public void setAntalRum(Integer antalRum) {
    this.antalRum = antalRum;
  }

  @JsonProperty("Yta")
  public Integer getYta() {
    return yta;
  }

  @JsonProperty("Yta")
  public void setYta(Integer yta) {
    this.yta = yta;
  }

  @JsonProperty("Hyra")
  public Integer getHyra() {
    return hyra;
  }

  @JsonProperty("Hyra")
  public void setHyra(Integer hyra) {
    this.hyra = hyra;
  }

  @JsonProperty("AnnonseradTill")
  public String getAnnonseradTill() {
    return annonseradTill;
  }

  @JsonProperty("AnnonseradTill")
  public void setAnnonseradTill(String annonseradTill) {
    this.annonseradTill = annonseradTill;
  }

  @JsonProperty("AnnonseradFran")
  public String getAnnonseradFran() {
    return annonseradFran;
  }

  @JsonProperty("AnnonseradFran")
  public void setAnnonseradFran(String annonseradFran) {
    this.annonseradFran = annonseradFran;
  }

  @JsonProperty("Url")
  public String getUrl() {
    return url;
  }

  @JsonProperty("Url")
  public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty("Antal")
  public Integer getAntal() {
    return antal;
  }

  @JsonProperty("Antal")
  public void setAntal(Integer antal) {
    this.antal = antal;
  }

  @JsonProperty("Balkong")
  public Boolean getBalkong() {
    return balkong;
  }

  @JsonProperty("Balkong")
  public void setBalkong(Boolean balkong) {
    this.balkong = balkong;
  }

  @JsonProperty("Hiss")
  public Boolean getHiss() {
    return hiss;
  }

  @JsonProperty("Hiss")
  public void setHiss(Boolean hiss) {
    this.hiss = hiss;
  }

  @JsonProperty("Nyproduktion")
  public Boolean getNyproduktion() {
    return nyproduktion;
  }

  @JsonProperty("Nyproduktion")
  public void setNyproduktion(Boolean nyproduktion) {
    this.nyproduktion = nyproduktion;
  }

  @JsonProperty("Ungdom")
  public Boolean getUngdom() {
    return ungdom;
  }

  @JsonProperty("Ungdom")
  public void setUngdom(Boolean ungdom) {
    this.ungdom = ungdom;
  }

  @JsonProperty("Student")
  public Boolean getStudent() {
    return student;
  }

  @JsonProperty("Student")
  public void setStudent(Boolean student) {
    this.student = student;
  }

  @JsonProperty("Senior")
  public Boolean getSenior() {
    return senior;
  }

  @JsonProperty("Senior")
  public void setSenior(Boolean senior) {
    this.senior = senior;
  }

  @JsonProperty("Korttid")
  public Boolean getKorttid() {
    return korttid;
  }

  @JsonProperty("Korttid")
  public void setKorttid(Boolean korttid) {
    this.korttid = korttid;
  }

  @JsonProperty("Vanlig")
  public Boolean getVanlig() {
    return vanlig;
  }

  @JsonProperty("Vanlig")
  public void setVanlig(Boolean vanlig) {
    this.vanlig = vanlig;
  }

  @JsonProperty("Bostadssnabben")
  public Boolean getBostadssnabben() {
    return bostadssnabben;
  }

  @JsonProperty("Bostadssnabben")
  public void setBostadssnabben(Boolean bostadssnabben) {
    this.bostadssnabben = bostadssnabben;
  }

  @JsonProperty("Ko")
  public String getKo() {
    return ko;
  }

  @JsonProperty("Ko")
  public void setKo(String ko) {
    this.ko = ko;
  }

  @JsonProperty("KoNamn")
  public String getKoNamn() {
    return koNamn;
  }

  @JsonProperty("KoNamn")
  public void setKoNamn(String koNamn) {
    this.koNamn = koNamn;
  }

  @JsonProperty("Lagenhetstyp")
  public String getLagenhetstyp() {
    return lagenhetstyp;
  }

  @JsonProperty("Lagenhetstyp")
  public void setLagenhetstyp(String lagenhetstyp) {
    this.lagenhetstyp = lagenhetstyp;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return "ApartmentAd{" +
        "annonsId=" + annonsId +
        ", stadsdel='" + stadsdel + '\'' +
        ", gatuadress='" + gatuadress + '\'' +
        ", kommun='" + kommun + '\'' +
        ", vaning=" + vaning +
        ", antalRum=" + antalRum +
        ", yta=" + yta +
        '}';
  }
}
