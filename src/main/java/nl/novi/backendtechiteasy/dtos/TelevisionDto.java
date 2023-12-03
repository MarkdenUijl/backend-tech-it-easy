package nl.novi.backendtechiteasy.dtos;

import jakarta.validation.constraints.Past;
import nl.novi.backendtechiteasy.enums.AvailableSizes;
import nl.novi.backendtechiteasy.models.Television;

import java.time.LocalDate;

public class TelevisionDto {
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public AvailableSizes availableSizes;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public boolean smartTv;
    public boolean wifi;
    public boolean voiceControl;
    public boolean hdr;
    public boolean bluetooth;
    public boolean ambiLight;
    public int originalStock;
    public int sold;
    @Past
    public LocalDate dom;

    public static TelevisionDto fromTelevision(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.id = television.getId();
        televisionDto.type = television.getType();
        televisionDto.brand = television.getBrand();
        televisionDto.name = television.getName();
        televisionDto.price = television.getPrice();
        televisionDto.availableSizes = television.getAvailableSizes();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenType = television.getScreenType();
        televisionDto.screenQuality = television.getScreenQuality();
        televisionDto.smartTv = television.getSmartTv();
        televisionDto.wifi = television.getWifi();
        televisionDto.voiceControl = television.getVoiceControl();
        televisionDto.hdr = television.getHdr();
        televisionDto.bluetooth = television.getBluetooth();
        televisionDto.ambiLight = television.getAmbiLight();
        televisionDto.originalStock = television.getOriginalStock();
        televisionDto.sold = television.getSold();
        televisionDto.dom = television.getDom();

        return televisionDto;
    }
}
