package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEstado is a Querydsl query type for Estado
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEstado extends EntityPathBase<Estado> {

    private static final long serialVersionUID = -1672098024L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEstado estado = new QEstado("estado");

    public final StringPath abreviaturaEs = createString("abreviaturaEs");

    public final SetPath<Ciudad, QCiudad> ciudads = this.<Ciudad, QCiudad>createSet("ciudads", Ciudad.class, QCiudad.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombreEs = createString("nombreEs");

    public final QPais pai;

    public QEstado(String variable) {
        this(Estado.class, forVariable(variable), INITS);
    }

    public QEstado(Path<? extends Estado> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEstado(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEstado(PathMetadata<?> metadata, PathInits inits) {
        this(Estado.class, metadata, inits);
    }

    public QEstado(Class<? extends Estado> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pai = inits.isInitialized("pai") ? new QPais(forProperty("pai")) : null;
    }

}

