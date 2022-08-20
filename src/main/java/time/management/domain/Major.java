package time.management.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Major {

    @Id
    @Column(name = "MAJOR_NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "major")
    private List<Member> members = new ArrayList<>();

    public void createBasicMajor(String name){
        this.name = name;
    }
}
