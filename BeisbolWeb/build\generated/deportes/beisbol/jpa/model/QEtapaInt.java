package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEtapaInt is a Querydsl query type for EtapaInt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEtapaInt extends EntityPathBase<EtapaInt> {

    private static final long serialVersionUID = -211152098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEtapaInt etapaInt = new QEtapaInt("etapaInt");

    public final QEtapa etapa;

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QIdioma idioma;

    public final StringPath nombre = createString("nombre");

    public QEtapaInt(String variable) {
        this(EtapaInt.class, forVariable(variable), INITS);
    }

    public QEtapaInt(Path<? extends EtapaInt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEtapaInt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEtapaInt(PathMetadata<?> metadata, PathInits inits) {
        this(EtapaInt.class, metadata, inits);
    }

    public QEtapaInt(Class<? extends EtapaInt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.etapa = inits.isInitialized("etapa") ? new QEtapa(forProperty("etapa"), inits.get("etapa")) : null;
        this.idioma = inits.isInitialized("idioma") ? new QIdioma(forProperty("idioma")) : null;
    }

}

