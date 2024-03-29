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

public class AnnouncementIdDto {
    private Integer id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private AnnouncementDisplay announcementDisplay;
    @JsonIgnore
    private CategoryDto categoryId;
    public String getAnnouncementCategory() {
        return categoryId.getCategoryName();
    }
}
