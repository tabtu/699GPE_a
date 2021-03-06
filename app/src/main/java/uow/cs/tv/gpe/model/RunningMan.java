package uow.cs.tv.gpe.model;

import java.io.Serializable;

/**
 * Running Entity
 *
 * @author 	Tab Tu
 * @date	2018-10-22
 * @since	1.0
 *
 */

public class RunningMan extends Entitys implements Serializable {

    private String id;
    private String name;

    public RunningMan() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
