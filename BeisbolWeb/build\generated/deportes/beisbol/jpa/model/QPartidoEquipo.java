package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPartidoEquipo is a Querydsl query type for PartidoEquipo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPartidoEquipo extends EntityPathBase<PartidoEquipo> {

    private static final long serialVersionUID = 568829326L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPartidoEquipo partidoEquipo = new QPartidoEquipo("partidoEquipo");

    public final NumberPath<Byte> carreras = createNumber("carreras", Byte.class);

    public final QEquipo equipo;

    public final NumberPath<Byte> errores = createNumber("errores", Byte.class);

    public final DateTimePath<java.util.Date> fechaActualizacion = createDateTime("fechaActualizacion", java.util.Date.class);

    public final NumberPath<Byte> gano = createNumber("gano", Byte.class);

    public final NumberPath<Byte> hits = createNumber("hits", Byte.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath localia = createString("localia");

    public final QPartido partido;

    public final NumberPath<Byte> perdio = createNumber("perdio", Byte.class);

    public QPartidoEquipo(String variable) {
        this(PartidoEquipo.class, forVariable(variable), INITS);
    }

    public QPartidoEquipo(Path<? extends PartidoEquipo> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPartidoEquipo(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPartidoEquipo(PathMetadata<?> metadata, PathInits inits) {
        this(PartidoEquipo.class, metadata, inits);
    }

    public QPartidoEquipo(Class<? extends PartidoEquipo> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipo = inits.isInitialized("equipo") ? new QEquipo(forProperty("equipo"), inits.get("equipo")) : null;
        this.partido = inits.isInitialized("partido") ? new QPartido(forProperty("partido"), inits.get("partido")) : null;
    }

}

