package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFranquiciaHistoricoInt is a Querydsl query type for FranquiciaHistoricoInt
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFranquiciaHistoricoInt extends EntityPathBase<FranquiciaHistoricoInt> {

    private static final long serialVersionUID = -1731940288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFranquiciaHistoricoInt franquiciaHistoricoInt = new QFranquiciaHistoricoInt("franquiciaHistoricoInt");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final QFranquiciaHistorico franquiciaHistorico;

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final QIdioma idioma;

    public final StringPath nombreCompleto = createString("nombreCompleto");

    public QFranquiciaHistoricoInt(String variable) {
        this(FranquiciaHistoricoInt.class, forVariable(variable), INITS);
    }

    public QFranquiciaHistoricoInt(Path<? extends FranquiciaHistoricoInt> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaHistoricoInt(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFranquiciaHistoricoInt(PathMetadata<?> metadata, PathInits inits) {
        this(FranquiciaHistoricoInt.class, metadata, inits);
    }

    public QFranquiciaHistoricoInt(Class<? extends FranquiciaHistoricoInt> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franquiciaHistorico = inits.isInitialized("franquiciaHistorico") ? new QFranquiciaHistorico(forProperty("franquiciaHistorico"), inits.get("franquiciaHistorico")) : null;
        this.idioma = inits.isInitialized("idioma") ? new QIdioma(forProperty("idioma")) : null;
    }

}

