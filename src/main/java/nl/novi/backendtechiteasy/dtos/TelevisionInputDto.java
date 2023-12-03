package nl.novi.backendtechiteasy.dtos;

import jakarta.validation.constraints.Past;
import nl.novi.backendtechiteasy.enums.AvailableSizes;
import nl.novi.backendtechiteasy.models.Television;

import java.time.LocalDate;

public class TelevisionInputDto {
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

    public static Television toTelevision(TelevisionInputDto televisionDto) {
        Television television = new Television();

        television.setType(televisionDto.type);
        television.setBrand(televisionDto.brand);
        television.setName(televisionDto.name);
        television.setPrice(televisionDto.price);
        television.setAvailableSizes(televisionDto.availableSizes);
        television.setRefreshRate(televisionDto.refreshRate);
        television.setScreenType(televisionDto.screenType);
        television.setScreenQuality(televisionDto.screenQuality);
        television.setSmartTv(televisionDto.smartTv);
        television.setWifi(televisionDto.wifi);
        television.setVoiceControl(televisionDto.voiceControl);
        television.setHdr(televisionDto.hdr);
        television.setBluetooth(televisionDto.bluetooth);
        television.setAmbiLight(televisionDto.ambiLight);
        television.setOriginalStock(televisionDto.originalStock);
        television.setSold(televisionDto.sold);
        television.setDom(televisionDto.dom);

        return television;
    }
}
