package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QParticipante is a Querydsl query type for Participante
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParticipante extends EntityPathBase<Participante> {

    private static final long serialVersionUID = -1555890236L;

    private static final PathInits INITS = new PathInits("*", "equipos.franquiciaHistorico.franquicia.id", "temporada.id");

    public static final QParticipante participante = new QParticipante("participante");

    public final SetPath<Equipo, QEquipo> equipos = this.<Equipo, QEquipo>createSet("equipos", Equipo.class, QEquipo.class, INITS.get("equipos"));

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final NumberPath<Byte> invitado = createNumber("invitado", Byte.class);

    public final SetPath<Record, QRecord> records = this.<Record, QRecord>createSet("records", Record.class, QRecord.class, PathInits.DIRECT2);

    public final QTemporada temporada;

    public QParticipante(String variable) {
        this(Participante.class, forVariable(variable), INITS);
    }

    public QParticipante(Path<? extends Participante> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParticipante(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QParticipante(PathMetadata<?> metadata, PathInits inits) {
        this(Participante.class, metadata, inits);
    }

    public QParticipante(Class<? extends Participante> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.temporada = inits.isInitialized("temporada") ? new QTemporada(forProperty("temporada"), inits.get("temporada")) : null;
    }

}

