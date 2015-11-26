package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCiudad is a Querydsl query type for Ciudad
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCiudad extends EntityPathBase<Ciudad> {

    private static final long serialVersionUID = -1738558966L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCiudad ciudad = new QCiudad("ciudad");

    public final QEstado estado;

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombre = createString("nombre");

    public final SetPath<Parque, QParque> parques = this.<Parque, QParque>createSet("parques", Parque.class, QParque.class, PathInits.DIRECT2);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QCiudad(String variable) {
        this(Ciudad.class, forVariable(variable), INITS);
    }

    public QCiudad(Path<? extends Ciudad> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCiudad(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCiudad(PathMetadata<?> metadata, PathInits inits) {
        this(Ciudad.class, metadata, inits);
    }

    public QCiudad(Class<? extends Ciudad> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.estado = inits.isInitialized("estado") ? new QEstado(forProperty("estado"), inits.get("estado")) : null;
    }

}

