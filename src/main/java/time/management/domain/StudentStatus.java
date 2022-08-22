package time.management.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentStatus {
    재학("재학"), 휴학("휴학"), 군휴학("군휴학"), 신입("신입"), 졸업("졸업");

    private final String value;
}
