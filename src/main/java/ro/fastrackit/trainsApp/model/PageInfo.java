package ro.fastrackit.trainsApp.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageInfo {
    int totalPages;
    int totalElements;
    int crtPage;
    int pageSize;
}
