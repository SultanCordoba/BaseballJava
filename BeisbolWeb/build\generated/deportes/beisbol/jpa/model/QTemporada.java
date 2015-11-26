package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTemporada is a Querydsl query type for Temporada
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTemporada extends EntityPathBase<Temporada> {

    private static final long serialVersionUID = -1890864747L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemporada temporada = new QTemporada("temporada");

    public final SetPath<Etapa, QEtapa> etapas = this.<Etapa, QEtapa>createSet("etapas", Etapa.class, QEtapa.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final DatePath<java.util.Date> fechaFin = createDate("fechaFin", java.util.Date.class);

    public final DatePath<java.util.Date> fechaInicio = createDate("fechaInicio", java.util.Date.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QLigaHistorico ligaHistorico;

    public final StringPath nombre = createString("nombre");

    public final SetPath<Participante, QParticipante> participantes = this.<Participante, QParticipante>createSet("participantes", Participante.class, QParticipante.class, PathInits.DIRECT2);

    public final NumberPath<Byte> tempCompleta = createNumber("tempCompleta", Byte.class);

    public final NumberPath<Integer> tipoPlayOff = createNumber("tipoPlayOff", Integer.class);

    public QTemporada(String variable) {
        this(Temporada.class, forVariable(variable), INITS);
    }

    public QTemporada(Path<? extends Temporada> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTemporada(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTemporada(PathMetadata<?> metadata, PathInits inits) {
        this(Temporada.class, metadata, inits);
    }

    public QTemporada(Class<? extends Temporada> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ligaHistorico = inits.isInitialized("ligaHistorico") ? new QLigaHistorico(forProperty("ligaHistorico"), inits.get("ligaHistorico")) : null;
    }

}

