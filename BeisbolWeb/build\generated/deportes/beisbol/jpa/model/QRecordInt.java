package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRecordInt is a Querydsl query type for RecordInt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRecordInt extends EntityPathBase<RecordInt> {

    private static final long serialVersionUID = 1412643564L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordInt recordInt = new QRecordInt("recordInt");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QIdioma idioma;

    public final StringPath nombreGrupo = createString("nombreGrupo");

    public final QRecord record;

    public QRecordInt(String variable) {
        this(RecordInt.class, forVariable(variable), INITS);
    }

    public QRecordInt(Path<? extends RecordInt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRecordInt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRecordInt(PathMetadata<?> metadata, PathInits inits) {
        this(RecordInt.class, metadata, inits);
    }

    public QRecordInt(Class<? extends RecordInt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idioma = inits.isInitialized("idioma") ? new QIdioma(forProperty("idioma")) : null;
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

