package nl.novi.backendtechiteasy.models;

import jakarta.persistence.*;
import nl.novi.backendtechiteasy.enums.AvailableSizes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "televisions")
public class Television {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    @Column(name = "available_sizes")
    private AvailableSizes availableSizes;
    @Column(name = "refresh_rate")
    private Double refreshRate;
    @Column(name = "screen_type")
    private String screenType;
    @Column(name = "screen_quality")
    private String screenQuality;
    @Column(name = "smart_tv")
    private boolean smartTv;
    private boolean wifi;
    @Column(name = "voice_control")
    private boolean voiceControl;
    private boolean hdr;
    private boolean bluetooth;
    @Column(name = "ambi_light")
    private boolean ambiLight;
    @Column(name = "original_stock")
    private int originalStock;
    private int sold;
    @Column(name = "date_of_manufacturing")
    @Temporal(TemporalType.DATE)
    private LocalDate dom;

    public LocalDate getDom() {
        return dom;
    }

    public void setDom(LocalDate dom) {
        this.dom = dom;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AvailableSizes getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(AvailableSizes availableSize) {
        this.availableSizes = availableSize;
    }

    public Double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public boolean getSmartTv() {
        return smartTv;
    }

    public void setSmartTv(Boolean smartTv) {
        this.smartTv = smartTv;
    }

    public boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public boolean getVoiceControl() {
        return voiceControl;
    }

    public void setVoiceControl(Boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public boolean getHdr() {
        return hdr;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean getAmbiLight() {
        return ambiLight;
    }

    public void setAmbiLight(Boolean ambiLight) {
        this.ambiLight = ambiLight;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
