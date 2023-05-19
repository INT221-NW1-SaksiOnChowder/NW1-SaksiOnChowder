package sit.int221.nw1apisas.Dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.nw1apisas.Enums.AnnouncementDisplay;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AnnouncementDto {
    private Integer id;
    private String announcementTitle;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private AnnouncementDisplay announcementDisplay;
    @JsonIgnore
    private CategoryDto categoryId;
    private Integer viewCount;
    public String getAnnouncementCategory() {
        return categoryId.getCategoryName();
    }

}
