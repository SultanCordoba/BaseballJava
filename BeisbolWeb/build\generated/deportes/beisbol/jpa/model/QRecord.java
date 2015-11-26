package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = -1313340925L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final QEtapa etapa;

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Byte> ganados = createNumber("ganados", Byte.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final StringPath nombreGrupo = createString("nombreGrupo");

    public final QParticipante participante;

    public final NumberPath<Byte> perdidos = createNumber("perdidos", Byte.class);

    public final SetPath<RecordInt, QRecordInt> recordInts = this.<RecordInt, QRecordInt>createSet("recordInts", RecordInt.class, QRecordInt.class, PathInits.DIRECT2);

    public final QVuelta vuelta;

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRecord(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRecord(PathMetadata<?> metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.etapa = inits.isInitialized("etapa") ? new QEtapa(forProperty("etapa"), inits.get("etapa")) : null;
        this.participante = inits.isInitialized("participante") ? new QParticipante(forProperty("participante"), inits.get("participante")) : null;
        this.vuelta = inits.isInitialized("vuelta") ? new QVuelta(forProperty("vuelta")) : null;
    }

}

