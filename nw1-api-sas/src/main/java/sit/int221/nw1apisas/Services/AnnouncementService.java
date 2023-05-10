package sit.int221.nw1apisas.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.nw1apisas.Dtos.AnnouncementItemDto;
import sit.int221.nw1apisas.Entities.Announcement;
import sit.int221.nw1apisas.Enums.AnnouncementDisplay;
import sit.int221.nw1apisas.Repositories.AnnouncementRepository;


import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CategoryService categoryService;

    public Announcement createAnnouncement(AnnouncementItemDto announcementItemDto) {
        Announcement announcement = new Announcement();
        if (announcementItemDto.getAnnouncementTitle() != null && announcementItemDto.getAnnouncementTitle().trim() != ""
                && announcementItemDto.getAnnouncementTitle().length() > 0 && announcementItemDto.getAnnouncementTitle().length() <= 200) {
            announcement.setAnnouncementTitle(announcementItemDto.getAnnouncementTitle());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a announcement title and must be less than or equal 200 characters");
        }
        if (announcementItemDto.getAnnouncementDescription() != null && announcementItemDto.getAnnouncementDescription().trim() != ""
                && announcementItemDto.getAnnouncementDescription().length() > 0 && announcementItemDto.getAnnouncementDescription().length() <= 10000) {
            announcement.setAnnouncementDescription(announcementItemDto.getAnnouncementDescription());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a announcement description and must be less than or equal 10000 characters");
        }
        if (announcementItemDto.getCategoryId() != null) {
            announcement.setCategoryId(categoryService.getCategoryById(announcementItemDto.getCategoryId()));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a category");
        }
        announcement.setPublishDate(announcementItemDto.getPublishDate());
        announcement.setCloseDate(announcementItemDto.getCloseDate());
        announcement.setAnnouncementDisplay(announcementItemDto.getAnnouncementDisplay());
        return announcementRepository.saveAndFlush(announcement);

    }

    public void deleteAnnouncement(Integer id) {
        announcementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The announcement is not found"));
        announcementRepository.deleteById(id);
    }

    public Announcement updateAnnouncement(AnnouncementItemDto announcementItemDto, Integer id) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The announcement is not found"));
        if (announcementItemDto.getAnnouncementTitle() != null && announcementItemDto.getAnnouncementTitle().trim() != ""
                && announcementItemDto.getAnnouncementTitle().length() > 0 && announcementItemDto.getAnnouncementTitle().length() <= 200) {
            existingAnnouncement.setAnnouncementTitle(announcementItemDto.getAnnouncementTitle());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a announcement title and must be less than or equal 200 characters");
        }
        if (announcementItemDto.getAnnouncementDescription() != null && announcementItemDto.getAnnouncementDescription().trim() != ""
                && announcementItemDto.getAnnouncementDescription().length() > 0 && announcementItemDto.getAnnouncementDescription().length() <= 10000) {
            existingAnnouncement.setAnnouncementDescription(announcementItemDto.getAnnouncementDescription());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a announcement description and must be less than or equal 10000 characters");
        }
        if (announcementItemDto.getCategoryId() != null) {
            existingAnnouncement.setCategoryId(categoryService.getCategoryById(announcementItemDto.getCategoryId()));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please fill a category");
        }
        existingAnnouncement.setAnnouncementTitle(announcementItemDto.getAnnouncementTitle());
        existingAnnouncement.setAnnouncementDescription(announcementItemDto.getAnnouncementDescription());
        existingAnnouncement.setPublishDate(announcementItemDto.getPublishDate());
        existingAnnouncement.setCloseDate(announcementItemDto.getCloseDate());
        existingAnnouncement.setAnnouncementDisplay(announcementItemDto.getAnnouncementDisplay());
        return announcementRepository.saveAndFlush(existingAnnouncement);
    }

    public List<Announcement> getAllAnnouncements(String mode) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        AnnouncementDisplay announcementDisplay = AnnouncementDisplay.Y;
        if (mode.equals("active")) {
            List<Announcement> announcements = announcementRepository.findActiveAnnouncement(announcementDisplay, currentTime);
            if (announcements == null || announcements.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No announcement");
            }
            return announcements;
        } else if (mode.equals("close")) {
            List<Announcement> announcements = announcementRepository.findCloseAnnouncement(announcementDisplay, currentTime);
            if (announcements == null || announcements.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No announcement");
            }
            return announcements;
        } else {
            return announcementRepository.findAllAnnouncementsByIdDesc();
        }

    }

    public boolean isActive(Announcement announcement){
        ZonedDateTime currentTime = ZonedDateTime.now();
        if (announcement.getAnnouncementDisplay() == AnnouncementDisplay.Y && (announcement.getPublishDate() == null
                ||  currentTime.isAfter(announcement.getPublishDate()) || currentTime.isEqual(announcement.getPublishDate()))
                && (announcement.getCloseDate() == null || currentTime.isBefore(announcement.getCloseDate()))){
            return true;
        }
        return false;
    }
    public boolean isClose(Announcement announcement) {
        ZonedDateTime currentTime = ZonedDateTime.now();
        if (announcement.getAnnouncementDisplay() == AnnouncementDisplay.Y &&
                (announcement.getCloseDate() != null && (currentTime.isAfter(announcement.getCloseDate()) || currentTime.isEqual(announcement.getCloseDate())))) {
            return true;
        }
        return false;
    }

    public Announcement getDetailsById(Integer id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement not found"));
        return announcement;
    }

    public Page<Announcement> getAnnouncementWithPagination(int page, int size, String sortBy, String mode) {
        Sort sort = Sort.by(sortBy).descending();
        AnnouncementDisplay announcementDisplayShow = AnnouncementDisplay.Y;
        ZonedDateTime currentTime = ZonedDateTime.now();
        if (mode.equals("active")) {
            return announcementRepository.findActiveAnnouncementWithPagination(announcementDisplayShow, currentTime, PageRequest.of(page, size, sort));
        } else if (mode.equals("close")) {
            return announcementRepository.findCloseAnnouncementWithPagination(announcementDisplayShow, currentTime, PageRequest.of(page, size, sort));
        } else {
            return announcementRepository.findAll(PageRequest.of(page, size, sort));
        }
    }

}


