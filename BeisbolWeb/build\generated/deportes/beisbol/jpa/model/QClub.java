package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QClub is a Querydsl query type for Club
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QClub extends EntityPathBase<Club> {

    private static final long serialVersionUID = 1678638824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClub club = new QClub("club");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final SetPath<Franquicia, QFranquicia> franquicias = this.<Franquicia, QFranquicia>createSet("franquicias", Franquicia.class, QFranquicia.class, PathInits.DIRECT2);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombre = createString("nombre");

    public final QPais pai;

    public QClub(String variable) {
        this(Club.class, forVariable(variable), INITS);
    }

    public QClub(Path<? extends Club> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClub(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QClub(PathMetadata<?> metadata, PathInits inits) {
        this(Club.class, metadata, inits);
    }

    public QClub(Class<? extends Club> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pai = inits.isInitialized("pai") ? new QPais(forProperty("pai")) : null;
    }

}

