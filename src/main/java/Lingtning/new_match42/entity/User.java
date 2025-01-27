package Lingtning.new_match42.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "intra", "email", "role"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String intra;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserConnectInterest> userConnectInterest;

    @Column(nullable = false)
    private Long interestCount;

    @OneToMany(mappedBy = "user")
    private List<UserConnectBlockUser> userConnectBlockUser;

    @Column(nullable = false)
    private Long blockCount;

    @Builder
    public User(Long id, String email, String intra, Role role, List<UserConnectInterest> userConnectInterest, Long interestCount, List<UserConnectBlockUser> userConnectBlockUser, Long blockCount) {
        this.id = id;
        this.email = email;
        this.intra = intra;
        this.role = role;
        this.userConnectInterest = userConnectInterest;
        this.interestCount = interestCount;
        this.userConnectBlockUser = userConnectBlockUser;
        this.blockCount = blockCount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getKey()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.intra;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}