package org.i4di.doku.domain;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.i4di.common.domain.Timestamped;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Audited
public class Token extends Timestamped {

    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime expireTime;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(DateTime expireTime) {
        this.expireTime = expireTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
