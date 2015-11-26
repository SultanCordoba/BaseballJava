package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPais is a Querydsl query type for Pais
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPais extends EntityPathBase<Pais> {

    private static final long serialVersionUID = 1679015181L;

    public static final QPais pais = new QPais("pais");

    public final StringPath abreviaturaEs = createString("abreviaturaEs");

    public final SetPath<Club, QClub> clubs = this.<Club, QClub>createSet("clubs", Club.class, QClub.class, PathInits.DIRECT2);

    public final SetPath<Estado, QEstado> estados = this.<Estado, QEstado>createSet("estados", Estado.class, QEstado.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Byte> id = createNumber("id", Byte.class);

    public final StringPath nombreEs = createString("nombreEs");

    public QPais(String variable) {
        super(Pais.class, forVariable(variable));
    }

    public QPais(Path<? extends Pais> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPais(PathMetadata<?> metadata) {
        super(Pais.class, metadata);
    }

}

