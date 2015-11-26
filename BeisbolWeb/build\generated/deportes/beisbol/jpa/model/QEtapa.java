package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEtapa is a Querydsl query type for Etapa
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEtapa extends EntityPathBase<Etapa> {

    private static final long serialVersionUID = 500262673L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEtapa etapa = new QEtapa("etapa");

    public final SetPath<EtapaInt, QEtapaInt> etapaInts = this.<EtapaInt, QEtapaInt>createSet("etapaInts", EtapaInt.class, QEtapaInt.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Byte> ordenRonda = createNumber("ordenRonda", Byte.class);

    public final SetPath<Partido, QPartido> partidos = this.<Partido, QPartido>createSet("partidos", Partido.class, QPartido.class, PathInits.DIRECT2);

    public final NumberPath<Short> previoId = createNumber("previoId", Short.class);

    public final SetPath<Record, QRecord> records = this.<Record, QRecord>createSet("records", Record.class, QRecord.class, PathInits.DIRECT2);

    public final NumberPath<Byte> rondaId = createNumber("rondaId", Byte.class);

    public final QTemporada temporada;

    public QEtapa(String variable) {
        this(Etapa.class, forVariable(variable), INITS);
    }

    public QEtapa(Path<? extends Etapa> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEtapa(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEtapa(PathMetadata<?> metadata, PathInits inits) {
        this(Etapa.class, metadata, inits);
    }

    public QEtapa(Class<? extends Etapa> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.temporada = inits.isInitialized("temporada") ? new QTemporada(forProperty("temporada"), inits.get("temporada")) : null;
    }

}

