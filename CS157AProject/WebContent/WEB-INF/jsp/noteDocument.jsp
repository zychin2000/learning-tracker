<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="note" type="sjsu.cs157a.model.DocumentNote"--%>
<%--@elvariable id="learningPrinciples" type="java.util.Set<sjsu.cs157a.model.LearningPrinciple>"--%>
<%--
  Created by IntelliJ IDEA.
  User: Zheng Yao
  Date: 11/27/2020
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Learn To Seek Truth | Note</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/editorjs@latest"></script>
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/header@latest"></script><!-- Header -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/simple-image@latest"></script><!-- Image -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/delimiter@latest"></script><!-- Delimiter -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/list@latest"></script><!-- List -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/checklist@latest"></script><!-- Checklist -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/quote@latest"></script><!-- Quote -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/code@latest"></script><!-- Code -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/embed@latest"></script><!-- Embed -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/table@latest"></script><!-- Table -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/link@latest"></script><!-- Link -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/warning@latest"></script><!-- Warning -->

    <script src="https://cdn.jsdelivr.net/npm/@editorjs/marker@latest"></script><!-- Marker -->
    <script src="https://cdn.jsdelivr.net/npm/@editorjs/inline-code@latest"></script><!-- Inline Code -->
    <%----%>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        /*Settings for editor.js*/
        .ce-block__content,
        .ce-toolbar__content {
            max-width: 1200px;
        }

        .shadowContainer {
            box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
            -webkit-box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
            -moz-box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
        }

    </style>
</head>

<body>
<jsp:include page="components/dashboardHeader.jsp"/>
<div class="row">
    <jsp:include page="components/dashboardSidebar.jsp"/>
    <div class="col-md-6">

        <h1 style="align-self: center">${note.getTitle()}</h1>
        <div id="editorjs" class="shadowContainer" style="border-style: solid;border-width: 0.5px; padding: 1em "></div>
    </div>
    <div class=col-md-4">
        <button class="btn btn-secondary btn-lg" type="button" id="saveButton" style="margin: 1em">Save</button>
        <div class=container>

            <jsp:include page="components/learningPrinciples.jsp">
                <jsp:param name="learningPrinciples" value="${learningPrinciples}"/>
                <jsp:param name="note" value="${note}"/>
            </jsp:include>
        </div>

    </div>

    <jsp:include page="components/comments.jsp">
        <jsp:param name="comments" value="${comments}"/>
    </jsp:include>

    <script>

        /**
         * To initialize the Editor, create a new instance with configuration object
         * @see docs/installation.md for mode details
         */
        function save() {
            editor.save()
                .then((savedData) => {
                    const urlSearchParams = new URLSearchParams();
                    urlSearchParams.append('note_id', ${note.getNote_id()})
                    urlSearchParams.append('content', JSON.stringify(savedData))
                    fetch('${pageContext.request.contextPath}/dashboard/note', {
                        method: 'post',
                        body: urlSearchParams,
                    })

                    //cPreview.show(savedData, document.getElementById("output"));
                })
                .catch((error) => {
                    console.error('Saving error', error);
                });
        }

        var editor = new EditorJS({
            /**
             * Enable/Disable the read only mode
             */
            readOnly: false,

            placeholder: 'Let`s write an awesome story!',


            /**
             * Wrapper of Editor
             */
            holder: 'editorjs',

            /**
             * Common Inline Toolbar settings
             * - if true (or not specified), the order from 'tool' property will be used
             * - if an array of tool names, this order will be used
             */
            // inlineToolbar: ['link', 'marker', 'bold', 'italic'],
            // inlineToolbar: true,

            /**
             * Tools list
             */
            tools: {
                /**
                 * Each Tool is a Plugin. Pass them via 'class' option with necessary settings {@link docs/tools.md}
                 */
                header: {
                    class: Header,
                    inlineToolbar: ['marker', 'link'],
                    config: {
                        placeholder: 'Header'
                    },
                    shortcut: 'CMD+SHIFT+H'
                },

                /**
                 * Or pass class directly without any configuration
                 */
                image: SimpleImage,

                list: {
                    class: List,
                    inlineToolbar: true,
                    shortcut: 'CMD+SHIFT+L'
                },

                checklist: {
                    class: Checklist,
                    inlineToolbar: true,
                },

                quote: {
                    class: Quote,
                    inlineToolbar: true,
                    config: {
                        quotePlaceholder: 'Enter a quote',
                        captionPlaceholder: 'Quote\'s author',
                    },
                    shortcut: 'CMD+SHIFT+O'
                },

                warning: Warning,

                marker: {
                    class: Marker,
                    shortcut: 'CMD+SHIFT+M'
                },

                code: {
                    class: CodeTool,
                    shortcut: 'CMD+SHIFT+C'
                },

                delimiter: Delimiter,

                inlineCode: {
                    class: InlineCode,
                    shortcut: 'CMD+SHIFT+C'
                },

                linkTool: LinkTool,

                embed: Embed,

                table: {
                    class: Table,
                    inlineToolbar: true,
                    shortcut: 'CMD+ALT+T'
                },

            },

            /**
             * This Tool will be used as default
             */
            // defaultBlock: 'paragraph',

            /**
             * Initial Editor data
             */
            data: ${note.getDocumentContent() == null? "[]" : note.getDocumentContent().toString()},
            onReady: function () {
                saveButton.click();
            },
            onChange: function () {
                console.log('something changed');
            }
        });

        /**
         * Saving button
         */
        const saveButton = document.getElementById('saveButton');

        /**
         * Toggle read-only button
         */
        const toggleReadOnlyButton = document.getElementById('toggleReadOnlyButton');
        const readOnlyIndicator = document.getElementById('readonly-state');

        /**
         * Saving example
         */
        saveButton.addEventListener('click', save);

        /**
         * Toggle read-only example
         */
        toggleReadOnlyButton.addEventListener('click', async () => {
            const readOnlyState = await editor.readOnly.toggle();

            readOnlyIndicator.textContent = readOnlyState ? 'On' : 'Off';
        });
    </script>

</div>



</body>
</html>
