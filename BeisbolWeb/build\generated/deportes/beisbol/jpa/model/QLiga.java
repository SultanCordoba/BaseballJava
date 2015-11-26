package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLiga is a Querydsl query type for Liga
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLiga extends EntityPathBase<Liga> {

    private static final long serialVersionUID = 1678903625L;

    public static final QLiga liga = new QLiga("liga");

    public final NumberPath<Byte> activa = createNumber("activa", Byte.class);

    public final StringPath descripcionBreveEn = createString("descripcionBreveEn");

    public final StringPath descripcionBreveEs = createString("descripcionBreveEs");

    public final NumberPath<Byte> disponible = createNumber("disponible", Byte.class);

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final SetPath<Franquicia, QFranquicia> franquicias = this.<Franquicia, QFranquicia>createSet("franquicias", Franquicia.class, QFranquicia.class, PathInits.DIRECT2);

    public final SetPath<FranquiciaVista, QFranquiciaVista> franquiciaVistas = this.<FranquiciaVista, QFranquiciaVista>createSet("franquiciaVistas", FranquiciaVista.class, QFranquiciaVista.class, PathInits.DIRECT2);

    public final NumberPath<Byte> id = createNumber("id", Byte.class);

    public final SetPath<LigaHistorico, QLigaHistorico> ligaHistoricos = this.<LigaHistorico, QLigaHistorico>createSet("ligaHistoricos", LigaHistorico.class, QLigaHistorico.class, PathInits.DIRECT2);

    public final StringPath nombreEn = createString("nombreEn");

    public final StringPath nombreEs = createString("nombreEs");

    public final StringPath siglasEn = createString("siglasEn");

    public final StringPath siglasEs = createString("siglasEs");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QLiga(String variable) {
        super(Liga.class, forVariable(variable));
    }

    public QLiga(Path<? extends Liga> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLiga(PathMetadata<?> metadata) {
        super(Liga.class, metadata);
    }

}

