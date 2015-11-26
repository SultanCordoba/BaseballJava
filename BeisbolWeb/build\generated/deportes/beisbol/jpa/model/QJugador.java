package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QJugador is a Querydsl query type for Jugador
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QJugador extends EntityPathBase<Jugador> {

    private static final long serialVersionUID = -107627440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJugador jugador = new QJugador("jugador");

    public final StringPath anyoNacimiento = createString("anyoNacimiento");

    public final StringPath apellidoMaterno = createString("apellidoMaterno");

    public final StringPath apellidoPaterno = createString("apellidoPaterno");

    public final StringPath apodo = createString("apodo");

    public final StringPath batea = createString("batea");

    public final StringPath bbRef = createString("bbRef");

    public final QCiudad ciudad;

    public final StringPath claveSABR = createString("claveSABR");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DatePath<java.util.Date> fechaNacimiento = createDate("fechaNacimiento", java.util.Date.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombreBusqueda = createString("nombreBusqueda");

    public final StringPath nombres = createString("nombres");

    public final StringPath posicion = createString("posicion");

    public final SetPath<Roster, QRoster> rosters = this.<Roster, QRoster>createSet("rosters", Roster.class, QRoster.class, PathInits.DIRECT2);

    public final StringPath tira = createString("tira");

    public QJugador(String variable) {
        this(Jugador.class, forVariable(variable), INITS);
    }

    public QJugador(Path<? extends Jugador> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJugador(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QJugador(PathMetadata<?> metadata, PathInits inits) {
        this(Jugador.class, metadata, inits);
    }

    public QJugador(Class<? extends Jugador> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ciudad = inits.isInitialized("ciudad") ? new QCiudad(forProperty("ciudad"), inits.get("ciudad")) : null;
    }

}

