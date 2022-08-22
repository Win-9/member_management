package time.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Position {
    회장("회장"), 부회장("부회장"), 총무("총무"), 홍보부장("홍보부장"), 편집부장("편집부장"),
    기획부장("기획부장"), 오락부장("오락부장"), 미화부장("미화부장"), 부원("부원");

    private final String value;

}
