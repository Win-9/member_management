package time.management.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "major")
    private List<Member> members = new ArrayList<>();

    public void createBasicMajor(String name){
        this.name = name;
    }

    public void deleteMember(Member member) {
        members.remove(member);
    }
}
