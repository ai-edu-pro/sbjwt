package org.zerock.sbjwt.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "tbl_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })

@SequenceGenerator(
        name="USER_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="SEQ_user", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GEN")
    private Long id;

    private String username;

    private String email;

    private String password;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){

        roles.add(role);
    }

}
