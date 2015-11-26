package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPartido is a Querydsl query type for Partido
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPartido extends EntityPathBase<Partido> {

    private static final long serialVersionUID = 360573551L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPartido partido = new QPartido("partido");

    public final StringPath comentario = createString("comentario");

    public final NumberPath<Byte> entradas = createNumber("entradas", Byte.class);

    public final QEtapa etapa;

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DatePath<java.util.Date> fechaRealizacion = createDate("fechaRealizacion", java.util.Date.class);

    public final NumberPath<Byte> forfeit = createNumber("forfeit", Byte.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final NumberPath<Byte> juegoDia = createNumber("juegoDia", Byte.class);

    public final NumberPath<Byte> mostrar = createNumber("mostrar", Byte.class);

    public final QParque parque;

    public final SetPath<PartidoEquipo, QPartidoEquipo> partidoEquipos = this.<PartidoEquipo, QPartidoEquipo>createSet("partidoEquipos", PartidoEquipo.class, QPartidoEquipo.class, PathInits.DIRECT2);

    public final StringPath partidoMilb = createString("partidoMilb");

    public final QVuelta vuelta;

    public QPartido(String variable) {
        this(Partido.class, forVariable(variable), INITS);
    }

    public QPartido(Path<? extends Partido> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPartido(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPartido(PathMetadata<?> metadata, PathInits inits) {
        this(Partido.class, metadata, inits);
    }

    public QPartido(Class<? extends Partido> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.etapa = inits.isInitialized("etapa") ? new QEtapa(forProperty("etapa"), inits.get("etapa")) : null;
        this.parque = inits.isInitialized("parque") ? new QParque(forProperty("parque"), inits.get("parque")) : null;
        this.vuelta = inits.isInitialized("vuelta") ? new QVuelta(forProperty("vuelta")) : null;
    }

}

