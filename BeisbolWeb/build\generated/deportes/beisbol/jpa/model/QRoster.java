package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRoster is a Querydsl query type for Roster
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRoster extends EntityPathBase<Roster> {

    private static final long serialVersionUID = -1303624643L;

    private static final PathInits INITS = new PathInits("*", "equipo.franquiciaHistorico.franquicia.liga.activa");

    public static final QRoster roster = new QRoster("roster");

    public final QEquipo equipo;

    public final DateTimePath<java.util.Date> fechaActualizacion = createDateTime("fechaActualizacion", java.util.Date.class);

    public final DatePath<java.util.Date> fechaFin = createDate("fechaFin", java.util.Date.class);

    public final DatePath<java.util.Date> fechaInicio = createDate("fechaInicio", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QJugador jugador;

    public final NumberPath<Byte> ordenEquipo = createNumber("ordenEquipo", Byte.class);

    public final StringPath posicion = createString("posicion");

    public QRoster(String variable) {
        this(Roster.class, forVariable(variable), INITS);
    }

    public QRoster(Path<? extends Roster> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoster(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoster(PathMetadata<?> metadata, PathInits inits) {
        this(Roster.class, metadata, inits);
    }

    public QRoster(Class<? extends Roster> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipo = inits.isInitialized("equipo") ? new QEquipo(forProperty("equipo"), inits.get("equipo")) : null;
        this.jugador = inits.isInitialized("jugador") ? new QJugador(forProperty("jugador"), inits.get("jugador")) : null;
    }

}

