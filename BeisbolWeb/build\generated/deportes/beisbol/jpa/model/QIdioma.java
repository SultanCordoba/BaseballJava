package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QIdioma is a Querydsl query type for Idioma
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIdioma extends EntityPathBase<Idioma> {

    private static final long serialVersionUID = -1571748217L;

    public static final QIdioma idioma = new QIdioma("idioma");

    public final StringPath abreviatura = createString("abreviatura");

    public final SetPath<EtapaInt, QEtapaInt> etapaInts = this.<EtapaInt, QEtapaInt>createSet("etapaInts", EtapaInt.class, QEtapaInt.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final SetPath<FranquiciaHistoricoInt, QFranquiciaHistoricoInt> franquiciaHistoricoInts = this.<FranquiciaHistoricoInt, QFranquiciaHistoricoInt>createSet("franquiciaHistoricoInts", FranquiciaHistoricoInt.class, QFranquiciaHistoricoInt.class, PathInits.DIRECT2);

    public final NumberPath<Short> id = createNumber("id", Short.class);

    public final SetPath<LigaHistoricoInt, QLigaHistoricoInt> ligaHistoricoInts = this.<LigaHistoricoInt, QLigaHistoricoInt>createSet("ligaHistoricoInts", LigaHistoricoInt.class, QLigaHistoricoInt.class, PathInits.DIRECT2);

    public final StringPath nombre = createString("nombre");

    public final SetPath<RecordInt, QRecordInt> recordInts = this.<RecordInt, QRecordInt>createSet("recordInts", RecordInt.class, QRecordInt.class, PathInits.DIRECT2);

    public QIdioma(String variable) {
        super(Idioma.class, forVariable(variable));
    }

    public QIdioma(Path<? extends Idioma> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdioma(PathMetadata<?> metadata) {
        super(Idioma.class, metadata);
    }

}

