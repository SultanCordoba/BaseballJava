package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFranquiciaVista is a Querydsl query type for FranquiciaVista
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFranquiciaVista extends EntityPathBase<FranquiciaVista> {

    private static final long serialVersionUID = -2025024692L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFranquiciaVista franquiciaVista = new QFranquiciaVista("franquiciaVista");

    public final QFranquicia franquicia;

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QLiga liga;

    public final StringPath nombreCompletoEn = createString("nombreCompletoEn");

    public final StringPath nombreCompletoEs = createString("nombreCompletoEs");

    public final StringPath nombrePais = createString("nombrePais");

    public final StringPath nombreTablasEs = createString("nombreTablasEs");

    public QFranquiciaVista(String variable) {
        this(FranquiciaVista.class, forVariable(variable), INITS);
    }

    public QFranquiciaVista(Path<? extends FranquiciaVista> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaVista(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaVista(PathMetadata<?> metadata, PathInits inits) {
        this(FranquiciaVista.class, metadata, inits);
    }

    public QFranquiciaVista(Class<? extends FranquiciaVista> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franquicia = inits.isInitialized("franquicia") ? new QFranquicia(forProperty("franquicia"), inits.get("franquicia")) : null;
        this.liga = inits.isInitialized("liga") ? new QLiga(forProperty("liga")) : null;
    }

}

